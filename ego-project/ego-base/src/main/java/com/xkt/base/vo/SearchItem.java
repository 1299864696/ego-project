package com.xkt.base.vo;

/**
 * 自定义商品搜索类
 *
 */
public class SearchItem {

	private Long id;
	private String title;
	private String sellPoint;
	private Long price;
	private String image;
	private String categoryName;

	private String[] images;

	public String[] getImages() {
		if (image != null) {
			images = this.image.split(",");
		}
		return images;
	}

	public SearchItem() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSellPoint() {
		return sellPoint;
	}

	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
