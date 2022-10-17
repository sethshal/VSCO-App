package shalini.mvvm.vsco.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Hits POJO class. Note this does not have any room references yet as the data is not
 * persisted locally for the purpose of this demo. I have for now used all of the returned JSON
 *
 */

public class Hit implements Serializable {

        @SerializedName("id")
        private Integer id;
        @SerializedName("pageURL")
        private String pageURL;
        @SerializedName("type")
        private String type;
        @SerializedName("tags")
        private String tags;
        @SerializedName("previewURL")
        private String previewURL;
        @SerializedName("previewWidth")
        private Integer previewWidth;
        @SerializedName("previewHeight")
        private Integer previewHeight;
        @SerializedName("webformatURL")
        private String webformatURL;
        @SerializedName("webformatWidth")
        private Integer webformatWidth;
        @SerializedName("webformatHeight")
        private Integer webformatHeight;
        @SerializedName("largeImageURL")
        private String largeImageURL;
        @SerializedName("fullHDURL")
        private String fullHDURL;
        @SerializedName("imageURL")
        private String imageURL;
        @SerializedName("imageWidth")
        private Integer imageWidth;
        @SerializedName("imageHeight")
        private Integer imageHeight;
        @SerializedName("imageSize")
        private Integer imageSize;
        @SerializedName("views")
        private Integer views;
        @SerializedName("downloads")
        private Integer downloads;
        @SerializedName("likes")
        private Integer likes;
        @SerializedName("comments")
        private Integer comments;
        @SerializedName("user_id")
        private Integer userId;
        @SerializedName("user")
        private String user;
        @SerializedName("userImageURL")
        private String userImageURL;
        private final static long serialVersionUID = -5877418701018889753L;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getPageURL() {
            return pageURL;
        }

        public void setPageURL(String pageURL) {
            this.pageURL = pageURL;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getPreviewURL() {
            return previewURL;
        }

        public void setPreviewURL(String previewURL) {
            this.previewURL = previewURL;
        }

        public Integer getPreviewWidth() {
            return previewWidth;
        }

        public void setPreviewWidth(Integer previewWidth) {
            this.previewWidth = previewWidth;
        }

        public Integer getPreviewHeight() {
            return previewHeight;
        }

        public void setPreviewHeight(Integer previewHeight) {
            this.previewHeight = previewHeight;
        }

        public String getWebformatURL() {
            return webformatURL;
        }

        public void setWebformatURL(String webformatURL) {
            this.webformatURL = webformatURL;
        }

        public Integer getWebformatWidth() {
            return webformatWidth;
        }

        public void setWebformatWidth(Integer webformatWidth) {
            this.webformatWidth = webformatWidth;
        }

        public Integer getWebformatHeight() {
            return webformatHeight;
        }

        public void setWebformatHeight(Integer webformatHeight) {
            this.webformatHeight = webformatHeight;
        }

        public String getLargeImageURL() {
            return largeImageURL;
        }

        public void setLargeImageURL(String largeImageURL) {
            this.largeImageURL = largeImageURL;
        }

        public String getFullHDURL() {
            return fullHDURL;
        }

        public void setFullHDURL(String fullHDURL) {
            this.fullHDURL = fullHDURL;
        }

        public String getImageURL() {
            return imageURL;
        }

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
        }

        public Integer getImageWidth() {
            return imageWidth;
        }

        public void setImageWidth(Integer imageWidth) {
            this.imageWidth = imageWidth;
        }

        public Integer getImageHeight() {
            return imageHeight;
        }

        public void setImageHeight(Integer imageHeight) {
            this.imageHeight = imageHeight;
        }

        public Integer getImageSize() {
            return imageSize;
        }

        public void setImageSize(Integer imageSize) {
            this.imageSize = imageSize;
        }

        public Integer getViews() {
            return views;
        }

        public void setViews(Integer views) {
            this.views = views;
        }

        public Integer getDownloads() {
            return downloads;
        }

        public void setDownloads(Integer downloads) {
            this.downloads = downloads;
        }

        public Integer getLikes() {
            return likes;
        }

        public void setLikes(Integer likes) {
            this.likes = likes;
        }

        public Integer getComments() {
            return comments;
        }

        public void setComments(Integer comments) {
            this.comments = comments;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getUserImageURL() {
            return userImageURL;
        }

        public void setUserImageURL(String userImageURL) {
            this.userImageURL = userImageURL;
        }
}
