package org.wallride.core.service;

import java.io.Serializable;

public class ArticleDeleteRequest implements Serializable {

	private Long id;
	private String language;

	public Long getId() {
		return id;
	}

	public String getLanguage() {
		return language;
	}

	public static class Builder  {

		private Long id;
		private String language;

		public Builder() {
		}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder language(String language) {
			this.language = language;
			return this;
		}

		public ArticleDeleteRequest build() {
			ArticleDeleteRequest request = new ArticleDeleteRequest();
			request.id = id;
			request.language = language;
			return request;
		}
	}
}
