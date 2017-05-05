package com.techexpo.springboot.model.common;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
/**
 * @author Sravan
 *
 */
public class HousingData implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	
	private Double  crim; 
	private int    zn;
	private Double indus;
	private int  chas;
	private Double nox;
	private Double rm;
	private Double age;
	private Double dis;
	private int rad;
	private int tax;
	private Double ptratio;
	private Double b;
	private Double lstat;
	private Double medv;
	
		
	public HousingData() {
	}
	
	


	public HousingData(Double crim, int zn, Double indus, int chas, Double nox, Double rm, Double age, Double dis,
			int rad, int tax, Double ptratio, Double b, Double lstat, Double medv) {
		super();
		this.crim = crim;
		this.zn = zn;
		this.indus = indus;
		this.chas = chas;
		this.nox = nox;
		this.rm = rm;
		this.age = age;
		this.dis = dis;
		this.rad = rad;
		this.tax = tax;
		this.ptratio = ptratio;
		this.b = b;
		this.lstat = lstat;
		this.medv = medv;
	}





	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Double getCrim() {
		return crim;
	}


	public void setCrim(Double crim) {
		this.crim = crim;
	}


	public int getZn() {
		return zn;
	}


	public void setZn(int zn) {
		this.zn = zn;
	}


	public Double getIndus() {
		return indus;
	}


	public void setIndus(Double indus) {
		this.indus = indus;
	}


	public int getChas() {
		return chas;
	}


	public void setChas(int chas) {
		this.chas = chas;
	}


	public Double getNox() {
		return nox;
	}


	public void setNox(Double nox) {
		this.nox = nox;
	}


	public Double getRm() {
		return rm;
	}


	public void setRm(Double rm) {
		this.rm = rm;
	}


	public Double getAge() {
		return age;
	}


	public void setAge(Double age) {
		this.age = age;
	}


	public Double getDis() {
		return dis;
	}


	public void setDis(Double dis) {
		this.dis = dis;
	}


	public int getRad() {
		return rad;
	}


	public void setRad(int rad) {
		this.rad = rad;
	}


	public int getTax() {
		return tax;
	}


	public void setTax(int tax) {
		this.tax = tax;
	}


	public Double getPtratio() {
		return ptratio;
	}


	public void setPtratio(Double ptratio) {
		this.ptratio = ptratio;
	}


	public Double getB() {
		return b;
	}


	public void setB(Double b) {
		this.b = b;
	}


	public Double getLstat() {
		return lstat;
	}


	public void setLstat(Double lstat) {
		this.lstat = lstat;
	}


	public Double getMedv() {
		return medv;
	}


	public void setMedv(Double medv) {
		this.medv = medv;
	}


	@Override
	public String toString() {
		return "HousingData [id=" + id + ", crim=" + crim + ", zn=" + zn + ", indus=" + indus + ", chas=" + chas
				+ ", nox=" + nox + ", rm=" + rm + ", age=" + age + ", dis=" + dis + ", rad=" + rad + ", tax=" + tax
				+ ", ptratio=" + ptratio + ", b=" + b + ", lstat=" + lstat + ", medv=" + medv + "]";
	}

	
	
}
