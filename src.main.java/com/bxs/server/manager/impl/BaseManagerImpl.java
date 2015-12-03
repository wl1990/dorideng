package com.bxs.server.manager.impl;

import java.io.Serializable;
import java.util.List;







import com.bxs.server.dao.BaseDao;
import com.bxs.server.dao.impl.BaseDaoImpl;
import com.bxs.server.manager.BaseManager;
import com.bxs.server.model.BasePojo;

/**
 * 控制层 数据库基本操作
 * @author Administrator
 *
 * @param <T>
 * @param <PK>
 */
public class BaseManagerImpl<T extends BasePojo,PK extends Serializable> implements BaseManager<T,PK>{
	protected BaseDaoImpl thisDao;
	@Override
	public boolean dbAdd(T pojo) {
		try {
			if(pojo!=null)
			return thisDao.addEntity(pojo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean dbSave(T t) {	
		PK id = (PK) t.getId();
		if(id!=null){
			return dbUpdate(t);
		}else{
			return dbAdd(t);
		}
	}

	@Override
	public boolean dbUpdate(T pojo) {
		try {
			if(pojo!=null)
			return thisDao.updateEntity(pojo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean dbDelete(T pojo) {
		try {
			if(pojo!=null)
			return thisDao.deleteEntity(pojo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean dbDelete(List<T> list) {
		try{
			for(T t:list){
				dbDelete(t);
			}
			return true;
			}catch(Exception e){
				e.printStackTrace();
			}
		return false;
	}
	@Override
	public List<? extends Object> getMerPerPage(Class clazz, int currentPage,
			int size) {
		return thisDao.getPerPageList(clazz,currentPage,size);
	}

	@Override
	public int getMaxPage(int size, Class clazz) {
		return thisDao.getMaxPage(clazz,size);
	}

	@Override
	public int getAmount(Class clazz) {
		return thisDao.getAmount(clazz);
	}

	@Override
	public T getById(Class clazz,int id) {
		return (T) thisDao.getById(clazz, id);
	}

	@Override
	public T getById(int id) {
		return (T) thisDao.getById(id);
	}

	@Override
	public List<T> getAll() {
		return thisDao.getAll();
	}
}
