package store.bean;

import java.io.Serializable;
import java.util.Date;

public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer uid;
	private Integer goodsId;
	private Goods goods;
	private Integer isCollect;
	private Integer goodsCount;
	private String createdUser;
	private Date createdTime;
	private String modifiedUser;
	private Date modifiedTime;

	public Cart() {
		super();
	}

	public Cart(Integer id, Integer uid, Integer goodsId, Goods goods, Integer isCollect, Integer goodsCount,
			String createdUser, Date createdTime, String modifiedUser, Date modifiedTime) {
		super();
		this.id = id;
		this.uid = uid;
		this.goodsId = goodsId;
		this.goods = goods;
		this.isCollect = isCollect;
		this.goodsCount = goodsCount;
		this.createdUser = createdUser;
		this.createdTime = createdTime;
		this.modifiedUser = modifiedUser;
		this.modifiedTime = modifiedTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Integer getisCollect() {
		return isCollect;
	}

	public void setisCollect(Integer isCollect) {
		this.isCollect = isCollect;
	}

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdTime == null) ? 0 : createdTime.hashCode());
		result = prime * result + ((createdUser == null) ? 0 : createdUser.hashCode());
		result = prime * result + ((goods == null) ? 0 : goods.hashCode());
		result = prime * result + ((goodsCount == null) ? 0 : goodsCount.hashCode());
		result = prime * result + ((goodsId == null) ? 0 : goodsId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isCollect == null) ? 0 : isCollect.hashCode());
		result = prime * result + ((modifiedTime == null) ? 0 : modifiedTime.hashCode());
		result = prime * result + ((modifiedUser == null) ? 0 : modifiedUser.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		if (createdTime == null) {
			if (other.createdTime != null)
				return false;
		} else if (!createdTime.equals(other.createdTime))
			return false;
		if (createdUser == null) {
			if (other.createdUser != null)
				return false;
		} else if (!createdUser.equals(other.createdUser))
			return false;
		if (goods == null) {
			if (other.goods != null)
				return false;
		} else if (!goods.equals(other.goods))
			return false;
		if (goodsCount == null) {
			if (other.goodsCount != null)
				return false;
		} else if (!goodsCount.equals(other.goodsCount))
			return false;
		if (goodsId == null) {
			if (other.goodsId != null)
				return false;
		} else if (!goodsId.equals(other.goodsId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isCollect == null) {
			if (other.isCollect != null)
				return false;
		} else if (!isCollect.equals(other.isCollect))
			return false;
		if (modifiedTime == null) {
			if (other.modifiedTime != null)
				return false;
		} else if (!modifiedTime.equals(other.modifiedTime))
			return false;
		if (modifiedUser == null) {
			if (other.modifiedUser != null)
				return false;
		} else if (!modifiedUser.equals(other.modifiedUser))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", uid=" + uid + ", goodsId=" + goodsId + ", goods=" + goods + ", isCollect="
				+ isCollect + ", goodsCount=" + goodsCount + ", createdUser=" + createdUser + ", createdTime="
				+ createdTime + ", modifiedUser=" + modifiedUser + ", modifiedTime=" + modifiedTime + "]";
	}

}
