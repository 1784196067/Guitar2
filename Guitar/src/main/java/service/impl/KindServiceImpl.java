package service.impl;

import java.io.InputStream;
import java.util.List;

import dao.KindDao;
import domain.Kind;
import net.sf.json.JSONObject;
import service.KindService;

public class KindServiceImpl implements KindService {
		private KindDao kindDao;

		public void setKindDao(KindDao kindDao) {
			this.kindDao = kindDao;
		}

		@Override
		public InputStream getAllKind() {
			return kindDao.getAllKind();
		}

		@Override
		public Kind getByType(String type) {
			return kindDao.getByType(type);
		}

		@Override
		public void save(JSONObject jsonObject, String type) {
			Kind kind = kindDao.save(jsonObject, type);
			kindDao.update(kind);			
		}
		
}
