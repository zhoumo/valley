package mine.valley.base;

import java.sql.SQLException;
import java.util.List;

import mine.valley.model.Page;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

@Component
public class BaseDao extends HibernateDaoSupport {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void executeSQL(String sql) {
		jdbcTemplate.execute(sql);
	}

	@Autowired
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		super.setSessionFactory(sessionFacotry);
	}

	public <T extends BaseEntity> void save(T entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
	}

	public <T extends BaseEntity> void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	public <T extends BaseEntity> void deleteAll(List<T> list) {
		this.getHibernateTemplate().deleteAll(list);
	}

	@SuppressWarnings("unchecked")
	public <T extends BaseEntity> List<T> find(String hql, Object... params) {
		return this.getHibernateTemplate().find(hql, params);
	}

	public <T extends BaseEntity> T get(Class<T> entityClass, Long id) {
		return this.getHibernateTemplate().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public <T> Page<T> findByPage(final String hql, final Object[] params, final Page<T> page) {
		return (Page<T>) getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				for (int position = 0; position < params.length; position++) {
					query.setParameter(position, params[position]);
				}
				int totalCount = query.list().size();
				page.setTotalCount(totalCount);
				query.setFirstResult((page.getPageNo() - 1) * page.getPageSize());
				query.setMaxResults(page.getPageSize());
				if (totalCount % page.getPageSize() == 0) {
					page.setTotalPages(totalCount / page.getPageSize());
				} else {
					page.setTotalPages(totalCount / page.getPageSize() + 1);
				}
				page.setResult(query.list());
				return page;
			}
		});
	}
}
