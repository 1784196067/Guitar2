package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="kind")
public class Kind {
	@Id
	@GeneratedValue(generator="kId")
	@GenericGenerator(name="kId",strategy="increment")
	private Integer kId;
	
	@Column(unique=true) 
	private String type;
	
	@ElementCollection(targetClass=String.class,fetch=FetchType.EAGER)
	@CollectionTable(name="characters",joinColumns=@JoinColumn(name="kId",nullable=false))
	@OrderColumn(name="character")
	private List<String> instrumentSpec = new ArrayList<>();
	
	@OneToMany(targetEntity=Instrument.class,mappedBy="kind",cascade=CascadeType.ALL)
	private Set<Instrument> instrumentSet = new HashSet<>();
	
	public Kind() {
		super();
	}

	public Integer getkId() {
		return kId;
	}

	public void setkId(Integer kId) {
		this.kId = kId;
	}



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getInstrumentSpec() {
		return instrumentSpec;
	}

	public void setInstrumentSpec(List<String> instrumentSpec) {
		this.instrumentSpec = instrumentSpec;
	}

	public Set<Instrument> getInstrumentSet() {
		return instrumentSet;
	}

	public void setInstrumentSet(Set<Instrument> instrumentSet) {
		this.instrumentSet = instrumentSet;
	}
	
}
