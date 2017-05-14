package service.impl;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import dao.InstrumentDao;
import domain.Instrument;
import domain.Kind;
import net.sf.json.JSONObject;
import service.InstrumentService;
import service.KindService;

public class InstrumentServiceImpl implements InstrumentService{
	private InstrumentDao instrumentDao;

	public void setInstrumentDao(InstrumentDao instrumentDao) {
		this.instrumentDao = instrumentDao;
	}

	@Override
	public void addInstrument(Instrument instrument) {
		instrumentDao.save(instrument);
	}

	@Override
	public List<Instrument> search(Map<String, String> searchSpec) {
		// TODO Auto-generated method stub
		return instrumentDao.search(searchSpec);
	}

	@Override
	public void addInstrument(JSONObject jsonObject, Kind kind) {
		// TODO Auto-generated method stub
		Instrument instrument = instrumentDao.addInstrument(jsonObject, kind);
		instrumentDao.save(instrument);
	}

	@Override
	public JSONArray match(JSONObject jsonObject, String type) {
		// TODO Auto-generated method stub
		return instrumentDao.match(jsonObject, type);
	}
}
