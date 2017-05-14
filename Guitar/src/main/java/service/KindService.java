package service;

import java.io.InputStream;
import domain.Kind;

public interface KindService {
	public InputStream getAllKind();
	public Kind getByType(String type);
	public void save(net.sf.json.JSONObject jsonObject, String type);
}
