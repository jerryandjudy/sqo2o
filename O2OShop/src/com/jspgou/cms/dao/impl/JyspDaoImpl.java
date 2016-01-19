package com.jspgou.cms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.jspgou.cms.dao.JyspDao;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.Jysp;
import com.jspgou.cms.entity.Product;
import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;

/**
 * This class should preserve.
 * 
 * @preserve
 */
@Repository
public class JyspDaoImpl extends HibernateBaseDao<Jysp, Long> implements
		JyspDao {

	@Override
	public List<Jysp> getAllList() {
		// TODO Auto-generated method stub
		Criteria crit = createCriteria();
		crit.addOrder(Order.desc(Jysp.PROP_CREATETIME));
		List<Jysp> list = crit.list();
		return list;
	}

	@Override
	public Jysp findById(Long id) {
		// TODO Auto-generated method stub
		Jysp entity = get(id);
		return entity;
	}

	@Override
	public Jysp save(Jysp bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}

	@Override
	public Jysp deleteById(Long id) {
		// TODO Auto-generated method stub
		Jysp bean = findById(id);
		getSession().delete(bean);
		return bean;
	}

	@Override
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder.create("from Jysp bean where 1=1");
		if (isDisabled != null) {
			f.append(" and bean.isDisabled=" + isDisabled);
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	public List<Jysp> getChildListByIdAndSiteId(Product proParent,Long id, Long siteId) {
		// TODO Auto-generated method stub
		Jysp jysp = this.findById(id);
//		System.out.println(jysp.getProductSite().getProduct().getId());
		
		Finder f = Finder.create("from Jysp bean where 1=1");
		f.append(" and   bean.productSite.id in(select productSite1.id from ProductSite productSite1 where productSite1.product.parent.id in("+proParent.getId()+")  and productSite1.website.id=" + siteId + ")");
		f.append(" and bean.gys.id='" + jysp.getGys().getId() + "'");
		f.append(" order by bean.id desc");
		// String
		// sql="SELECT * FROM `jxc_gys_goods` where goodsid in(select productSite1.id from jc_shop_site_product productSite1 where productSite1.product_id in(select product.parent_id from jc_shop_site_product productSite,jc_shop_product product where product.id=productSite.product_id productSite.id="+jysp.getProductSite().getId()+")  and productSite1.website.id="+siteId+") and gysid='"+jysp.getGys().getId()+"'";
		return find(f);

	}

	@Override
	public Pagination getPageByIsDisabled(Long siteId, String username,
			String companyName, Boolean isDisabled, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder.create("from Jysp bean where 1=1");
		if (isDisabled != null) {
			f.append(" and bean.isDisabled=" + isDisabled);
		}
		if (username != null) {
			f.append(" and bean.ketaUser.realname like'%" + username + "%'");
		}
		if (siteId != null) {
			f.append(" and bean.website.id=" + siteId);
		}
		if (companyName != null) {
			f.append(" and bean.companyName like'%" + companyName + "%'");
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	protected Class<Jysp> getEntityClass() {
		// TODO Auto-generated method stub
		return Jysp.class;
	}

	@Override
	public Pagination getPageByGysId(Gys gys, int pageNo, int pageSize) {
		Finder f = Finder.create("from Jysp bean where 1=1");
		if (gys != null && gys.getId() != null) {
			f.append(" and bean.gys.id ='" + gys.getId() + "'");
		}
		f.append(" order by bean.createTime desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	public Pagination getJyspPage(Long siteId, String username,
			String companyName, String productName, Long typeId, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder
				.create("from Jysp bean where bean.gys.isDisabled=false");
		if (username != null) {
			f.append(" and bean.gys.ketaUser.realname like'%" + username + "%'");
		}
		if (siteId != null) {
			f.append(" and bean.gys.website.id=" + siteId);
		}
		if (companyName != null) {
			f.append(" and bean.gys.companyName like'%" + companyName + "%'");
		}
		if (productName != null) {
			f.append(" and bean.productSite.product.name like'%" + productName
					+ "%'");
		}
		if (typeId != null && typeId > 0) {
			f.append(" and bean.productSite.product.type.id=" + typeId);
		}
		f.append(" order by bean.goodsMinNum-bean.goodsNum desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	public Jysp findByProductIdAndKetaUserId(Long productId, Long ketaUserId) {
		String hql = "from Jysp bean where  1=1 ";
		if (productId != null) {
			hql = hql + " and bean.productSite.id=" + productId;
		}
		if (ketaUserId != null) {
			hql = hql + " and bean.gys.ketaUser.id=" + ketaUserId;
		}
		hql = hql + " order by bean.id";
		Jysp entity = (Jysp) findUnique(hql);

		return entity;
	}

	@Override
	public List<Gys> findGysByProductId(Long productId) {
		String hql = "select bean.gys from Jysp bean where  1=1 ";
		if (productId != null) {
			hql = hql
					+ " and bean.productSite.id in(select productSite.id from ProductSite productSite where productSite.product.id=?)";
		}

		hql = hql + " order by bean.id";

		return this.find(hql, productId);
	}

	@Override
	public List<Jysp> findByProductId(Long parentId, Long productId) {
		String hql = "from Jysp bean where  1=1 ";
		if (productId != null) {
			hql = hql
					+ " and bean.productSite.id in(select productSite.id from ProductSite productSite where productSite.product.id=?)";
		}
		if (parentId != null) {
			hql = hql
					+ " and bean.gys.id in(select jysp.gys.id from Jysp jysp where jysp.productSite.id in(select productSite.id from ProductSite productSite where productSite.product.id=?))";
		}

		hql = hql + " order by bean.id";

		return this.find(hql, parentId, productId);
	}

}