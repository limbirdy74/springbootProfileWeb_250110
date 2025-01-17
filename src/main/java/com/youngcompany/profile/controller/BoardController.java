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
					
			if(pageNum != null) {  // 그냥 list 요청으로 오면 pageNum 값이 null 이어서
				criteria.setPageNum(Integer.parseInt(pageNum));
				// 사용자가 클릭한 페이지 번호를 criteria 객체 내의 멤버변수인 pageNum 값으로 설정
			}

			int total = bDao.totalBoardCountDao(); // 게시판 내 모든 글의 갯수
			
			PageDto pageDto = new PageDto(total, criteria);
			
			//int realEndPage = (int) Math.ceil(total*1.0 / criteria.getAmount()); // 실제 마지막 페이지
			
			ArrayList<BoardDto> bDtos = bDao.listDao(criteria.getAmount(), criteria.getPageNum()); // 모든 글 가져오기 에서
			// 인수로 한페이지에 보여질 글의 갯수와 사용자가 클릭한 페이지의 번호를 입력해서 호출
			
			model.addAttribute("bDtos", bDtos);
			model.addAttribute("pageDto", pageDto);
			model.addAttribute("currentPage", criteria.getPageNum());
					
			return "board";
		}
		
		@GetMapping(value = "/contentView")
		public String contentView(HttpServletRequest request, Model model) {
			
			String bnum = request.getParameter("bnum"); // 사용자가 클릭한 글번호
			String currPage = request.getParameter("pageNum"); // 현재 사용자가 글 제목을 클릭했던 페이지의 번호
			BoardDao bDao = sqlSession.getMapper(BoardDao.class);
			
			bDao.updateHitDao(bnum); // 조회수 1 증가. 250117 추가
			
			BoardDto bDto = bDao.contentViewDao(bnum);
			
			model.addAttribute("bDto", bDto);
			model.addAttribute("currPage", currPage);
			
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
			
//			String bnum = request.getParameter("bnum"); // 사용자가 클릭한 글번호
//			String currPageNum = request.getParameter("pageNum");//삭제한 글이 있는 페이지 //250117 삭제한 글이 있는 페이지
//			
//			String sid = (String) session.getAttribute("sessionid");
//			
//			BoardDao bDao = sqlSession.getMapper(BoardDao.class);
//			BoardDto bDto = bDao.contentViewDao(bnum);
//			
//			if(sid.equals(bDto.getBid())) {
//				
////				int deleteFlag = bDao.contentDeleteDao(bnum);
////				
////				if(deleteFlag == 1) {
//				if(bDao.contentDeleteDao(bnum) == 1) {
//					model.addAttribute("msg", "글이 성공적으로 삭제되었습니다.");
//					model.addAttribute("url", "list?pageNum="+currPageNum);
//					
//					return "alert/alert";
//				} else {
//					model.addAttribute("msg", "글이 삭제가 실패하였습니다.");
//					model.addAttribute("url", "list");
//					
//					return "alert/alert";
//				}
//				
////				return "redirect:list";
//				
//			} else {
//				model.addAttribute("msg", "글을 작성한 사용자만 삭제권한이 있습니다.");
//				
//				return "alert/alert2";
//			}
			
			String bnum = request.getParameter("bnum");//삭제할 글의 번호
			String currPageNum = request.getParameter("pageNum");//삭제한 글이 있는 페이지
			
			BoardDao bDao = sqlSession.getMapper(BoardDao.class);
			BoardDto bDto = bDao.contentViewDao(bnum);//해당 글 번호의 모든 정보 가져오기
			
			String sid = (String) session.getAttribute("sessionid");//현재 로그인한 사용자의 아이디		
			
			if(sid.equals(bDto.getBid())) {//현재 로그인한 사용자 아이디와 글쓴사용자의 아이디 비교
				if(bDao.contentDeleteDao(bnum) == 1) {//참이면 삭제 성공
					model.addAttribute("msg", "글이 성공적으로 삭제되었습니다.");
					model.addAttribute("url", "list?pageNum="+currPageNum);
					
					return "alert/alert";
				} else {
					model.addAttribute("msg", "글 삭제가 실패하였습니다.");
					model.addAttribute("url", "list");
					
					return "alert/alert";//삭제 실패시 리스트로 돌아가기
				}
			} else {
				model.addAttribute("msg", "해당 글의 삭제 권한이 없습니다.");			
				return "alert/alert2";
			}
			

		}
}
