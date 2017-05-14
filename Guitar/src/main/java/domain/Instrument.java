package domain;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyClass;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="instrument")
public class Instrument {
	@Id
	@GeneratedValue(generator="serialNumber")
	@GenericGenerator(name="serialNumber",strategy="assigned")
	private String serialNumber;
	
	@Column
	private double price;
	
	
	@ElementCollection(targetClass=String.class)
	@CollectionTable(name="spec",joinColumns=@JoinColumn(name="serialNumber",nullable=false))
	@MapKeyColumn(name="propertyName")
	@MapKeyClass(String.class)
	@Column(name="propertyValue")
	private Map<String,String> instrumentSpec = new HashMap<String,String>();
	
	@ManyToOne(targetEntity=Kind.class)
	@JoinColumn(name="kId", referencedColumnName="kId")
	private Kind kind;
	
	public Instrument() {
		super();
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Map<String, String> getInstrumentSpec() {
		return instrumentSpec;
	}

	public void setInstrumentSpec(Map<String, String> instrumentSpec) {
		this.instrumentSpec = instrumentSpec;
	}

	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}

	public Boolean match(Map<String, String> data){
		Boolean bool = true;
		for (String in : data.keySet()) {
			if(!instrumentSpec.get(in).toLowerCase().equals(data.get(in).trim().toLowerCase()) && data.get(in).trim().length() > 0){
				bool = false;
				break;
			}
		}
		return bool;
	}
	 
}
