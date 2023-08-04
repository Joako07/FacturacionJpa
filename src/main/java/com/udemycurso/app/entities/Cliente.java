package com.udemycurso.app.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
//Con @Table le das un nombre a la tabla. Si se va llamar igual que en la db no hace falta
@Table(name="clientes")
public class Cliente implements Serializable{

	//Con @Id indicas que es la clave primaria 
	@Id 
	// @GenerateValue es para indicar como se va a generar. En este caso ponemos identity para que vaya de 1 en 1
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//En caso de que el atributo se llame distinto en la DB debes indicarlo asì
	//@Column (name="nombre_cliente")  Entre los parentecis pones como figura en la DB
	@NotEmpty
	@Size(min=4, max=20)
	private String nombre;
	@NotEmpty
	private String apellido;
	@NotEmpty
	@Email
	private String email;
	
	@NotNull
	@Column(name="create_at")
	//@Temporal es para indicar el formato de la fecha
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createAt;
	
	//Al ser una relación en ambas direcciones usamos el "mappedBy" este genera las claves foráneas autmaticamente. 
	@OneToMany(mappedBy="cliente", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Factura> facturas;
	
	public Cliente() {
		facturas= new ArrayList<Factura>();
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Date getCreateAt() {
		return createAt;
	}



	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public List<Factura> getFacturas() {
		return facturas;
	}



	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	//Este metodo agrega factura por factura(una por una) en la clase cliente.
	public void addFactura(Factura factura) {
		facturas.add(factura);
	}

	@Override
	public String toString() {
		return nombre + " " + apellido;
	}

	private static final long serialVersionUID = 1L;

}
