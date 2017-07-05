//package com.slbs.controller;
//
//import java.util.Date;
//import java.util.List;
//
//import javax.inject.Inject;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.SessionAttributes;
//
//import com.slbs.common.util.BaseResult;
//import com.slbs.common.util.StringUtil;
//import com.slbs.model.Constants;
//import com.slbs.model.forum.ForumMain;
//import com.slbs.model.forum.ForumVo;
//import com.slbs.model.user.User;
//
///*
// *璁哄潧 
// */
//@SessionAttributes(Constants.USER)
//@Controller
//public class ForumController {
//
//	private static final Logger log = LoggerFactory.getLogger(ForumController.class);
//
//	@Inject
//	private CardsService cardsService;
//
//	@RequestMapping(value = "card/addcards", method = RequestMethod.POST)
//	@ResponseBody
//	public BaseResult addCards(@ModelAttribute(Constants.USER) User user,
//			@RequestBody ForumVo forumVo) {
//		log.debug(new Date() + "鍙戝笘浜猴細" + user.getUsername() + " ;" + "甯栧瓙鏍囬锛�
//				+ forumVo.getForumHead() + ";" + "甯栧瓙鍐呭锛� + forumVo.getContent());
//		if (StringUtil.isNotEmpty(user.getId())) {
//			forumVo.setUserid(user.getUserid());
//			if (StringUtil.isNotEmpty(forumVo.getForumHead())) {
//				if (StringUtil.isNotEmpty(forumVo.getContent())) {
//					cardsService.addCard(forumVo);
//				} else {
//					return BaseResult.fail("璇疯緭鍏ュ笘瀛愬唴瀹�..");
//				}
//			} else {
//				return BaseResult.fail("甯栧瓙鏍囬涓嶈兘涓虹┖.....");
//			}
//
//		} else {
//			return BaseResult.fail("璇风櫥褰曘�銆傘�");
//		}
//		return BaseResult.successResult("鍙戦�鎴愬姛");
//	}
//
//	// 鍒嗛〉鏌ヨ甯栧瓙
//	@RequestMapping(value = "card/getcards/{start}/{end}", method = RequestMethod.POST)
//	@ResponseBody
//	public BaseResult<List<ForumMain>> getCards(@PathVariable long start,
//			@PathVariable long end) {
//		return BaseResult.successResult(cardsService.findCards(start, end));
//	}
//	
//	// 鍒嗛〉鏌ヨ甯栧瓙
//		@RequestMapping(value = "card/getcardbyid/{id}", method = RequestMethod.POST)
//		@ResponseBody
//		public BaseResult<List<ForumMain>> getCards(@PathVariable long id) {
//			return BaseResult.successResult(cardsService.findCardsbyid(id));
//		}
//	
//}
