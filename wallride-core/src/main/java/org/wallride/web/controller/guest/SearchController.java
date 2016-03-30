/*
 * Copyright 2014 Tagbangers, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wallride.web.controller.guest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wallride.domain.BlogLanguage;
import org.wallride.domain.Post;
import org.wallride.model.PostSearchRequest;
import org.wallride.service.PostService;
import org.wallride.web.support.Pagination;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@RequestMapping("/search")
public class SearchController {

	@Inject
	private PostService postService;

	@RequestMapping
	public String search(
			@RequestParam String keyword,
			@PageableDefault(50) Pageable pageable,
			BlogLanguage blogLanguage,
			Model model,
			HttpServletRequest servletRequest) {
		PostSearchRequest request = new PostSearchRequest(blogLanguage.getLanguage()).withKeyword(keyword);
		Page<Post> posts = postService.getPosts(request, pageable);
		model.addAttribute("keyword", keyword);
		model.addAttribute("posts", posts);
		model.addAttribute("pageable", pageable);
		model.addAttribute("pagination", new Pagination<>(posts, servletRequest));
		return "search";
	}
}
