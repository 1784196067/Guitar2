package action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import com.opensymphony.xwork2.ActionSupport;

import domain.Instrument;
import domain.Kind;
import net.sf.json.JSONObject;
import service.InstrumentService;
import service.KindService;

public class searchAction extends ActionSupport {
		private InstrumentService instrumentService;
		private KindService kindService;
		private InputStream inputStream;
		private String characters;
		private String type;
		
		public void setInstrumentService(InstrumentService instrumentService) {
			this.instrumentService = instrumentService;
		}

		public void setKindService(KindService kindService) {
			this.kindService = kindService;
		}

		public String getCharacters() {
			return characters;
		}

		public void setCharacters(String characters) {
			this.characters = characters;
		}
		
		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public InputStream getResult(){
				return inputStream;
		}
		 
		

		public String search() throws Exception{
			ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
			JSONObject jsonObject = JSONObject.fromObject(characters);
			JSONArray jsonArray = instrumentService.match(jsonObject, type);
			inputStream =new ByteArrayInputStream(jsonArray.toString().getBytes("UTF-8"));
			return SUCCESS;
		}
		
		public String addInstrument() throws Exception{
			ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
			JSONObject jsonObject = JSONObject.fromObject(characters);
			Kind kind = kindService.getByType(type);
			instrumentService.addInstrument(jsonObject, kind);
			inputStream =  new ByteArrayInputStream("success".getBytes("UTF-8"));
			return SUCCESS;
		}
		
		public String save() throws Exception{
			ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
			JSONObject jsonObject = JSONObject.fromObject(characters);
			kindService.save(jsonObject, type);
			inputStream =  new ByteArrayInputStream("success".getBytes("UTF-8"));
			return SUCCESS;
		}
		
		public String getAllKind() throws Exception{
			ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
			inputStream = kindService.getAllKind();
			return SUCCESS;
		}
}
