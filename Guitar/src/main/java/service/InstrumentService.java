package service;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import domain.Instrument;
import domain.Kind;
import net.sf.json.JSONObject;

public interface InstrumentService {
	public void addInstrument(Instrument instrument);
	public List<Instrument> search(Map<String,String> searchSpec);
	public void addInstrument(JSONObject jsonObject, Kind kind);
	public JSONArray match(JSONObject jsonObject, String type);
}
