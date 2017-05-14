package dao;

import java.io.InputStream;

import domain.Kind;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface KindDao extends BaseDao<Kind>{
	public Kind getByType(String type);
	public InputStream getAllKind();
	public Kind save(JSONObject jsonObject, String type);
}
