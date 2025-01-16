package com.youngcompany.profile.controller;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.youngcompany.profile.dao.BoardDao;
import com.youngcompany.profile.dao.MemberDao;
import com.youngcompany.profile.dto.BoardDto;
import com.youngcompany.profile.dto.Criteria;
import com.youngcompany.profile.dto.MemberDto;
import com.youngcompany.profile.dto.PageDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {
	
		@Autowired
		private SqlSession sqlSession;

		@GetMapping(value = "/write")
		public String write(HttpSession session, Model model) {
			
			String sid = (String) session.getAttribute("sessionid");
			
			if(sid != null) {  // 로그인 상태
			
				model.addAttribute("bid", sid);
				
				MemberDao mDao = sqlSession.getMapper(MemberDao.class);
				MemberDto mDto = mDao.memberInfoDao(sid);
				model.addAttribute("bname", mDto.getMname());
				
				return "writeForm";
			} else { // 비로그인 상태
				model.addAttribute("msg", "로그인한 회원만 글쓰기가 가능합니다. 로그인해주세요.");
				model.addAttribute("url", "login");
				
				return "alert/alert";
			}
		}
		
		@PostMapping(value = "/writeOk")
		public String writeOk(HttpServletRequest request, Model model) {
			
			String bid = request.getParameter("bid");
			String bname = request.getParameter("bname");
			String btitle = request.getParameter("btitle");
			String bcontent = request.getParameter("bcontent");
			
			BoardDao bDao = sqlSession.getMapper(BoardDao.class);
			bDao.writeDao(bid, bname, btitle, bcontent); // 글쓰기
			
			
			return "redirect:list";
		}
		
		@GetMapping(value = "/list")
		public String board(HttpServletRequest request, Model model, Criteria criteria) {
			
			BoardDao bDao= sqlSession.getMapper(BoardDao.class);
			
			String pageNum = request.getParameter("pageNum"); // 사용자가 클릭한 게시판의 페이지 번호
					
			criteria.setPageNum(Integer.parseInt(pageNum));
			// 사용자가 클릭한 페이지 번호를 criteria 객체 내의 멤버변수인 pageNum 값으로 설정
			int total = bDao.totalBoardCountDao(); // 게시판 내 모든 글의 갯수
			
			PageDto pageDto = new PageDto(total, criteria);
			
			ArrayList<BoardDto> bDtos = bDao.listDao(); // 모든 글 가져오기
			
			model.addAttribute("bDtos", bDtos);
					
			return "board";
		}
		
		@GetMapping(value = "/contentView")
		public String contentView(HttpServletRequest request, Model model) {
			
			String bnum = request.getParameter("bnum"); // 사용자가 클릭한 글번호
			
			BoardDao bDao = sqlSession.getMapper(BoardDao.class);
			BoardDto bDto = bDao.contentViewDao(bnum);
			
			model.addAttribute("bDto", bDto);
			
			return "contentView";
		}
		
		@GetMapping(value = "/contentModify")
		public String contentModify(HttpServletRequest request, Model model, HttpSession session) {
			
			String bnum = request.getParameter("bnum"); // 사용자가 클릭한 글번호
			String sid = (String) session.getAttribute("sessionid");
			
			BoardDao bDao = sqlSession.getMapper(BoardDao.class);
			BoardDto bDto = bDao.contentViewDao(bnum);
			
			if(sid.equals(bDto.getBid())) {
				
				model.addAttribute("bDto", bDto);
				
				return "contentModify";
				
			} else {
				model.addAttribute("msg", "글을 작성한 사용자만 수정권한이 있습니다.");
				
				return "alert/alert2";
			}
			

		}
		
		@PostMapping(value = "/contentModifyOk")
		public String contentModifyOk(HttpServletRequest request, Model model) {
			
			String bnum = request.getParameter("bnum");
			String btitle = request.getParameter("btitle");
			String bcontent = request.getParameter("bcontent");
			
			BoardDao bDao = sqlSession.getMapper(BoardDao.class);
			bDao.contentModifyDao(bnum, btitle, bcontent);
			
			return "redirect:list";
		}
		
		@GetMapping(value = "/contentDelete")
		public String contentDelete(HttpServletRequest request, Model model, HttpSession session) {
			
			String bnum = request.getParameter("bnum"); // 사용자가 클릭한 글번호
			String sid = (String) session.getAttribute("sessionid");
			
			BoardDao bDao = sqlSession.getMapper(BoardDao.class);
			BoardDto bDto = bDao.contentViewDao(bnum);
			
			if(sid.equals(bDto.getBid())) {
				
//				int deleteFlag = bDao.contentDeleteDao(bnum);
//				
//				if(deleteFlag == 1) {
				if(bDao.contentDeleteDao(bnum) == 1) {
					model.addAttribute("msg", "글이 성공적으로 삭제되었습니다.");
					model.addAttribute("url", "list");
					
					return "alert/alert";
				} else {
					model.addAttribute("msg", "글이 삭제가 실패하였습니다.");
					model.addAttribute("url", "list");
					
					return "alert/alert";
				}
				
//				return "redirect:list";
				
			} else {
				model.addAttribute("msg", "글을 작성한 사용자만 삭제권한이 있습니다.");
				
				return "alert/alert2";
			}
			

		}
}
