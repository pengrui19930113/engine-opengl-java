package pengrui.javagl.abstraction.datas.impl;

import pengrui.javagl.abstraction.basics.Vector3f;
import pengrui.javagl.abstraction.basics.Vector4f;

import pengrui.javagl.abstraction.datas.Colorable;

public class Color implements Colorable{

	public Color() {
		member = new Vector4f();
	}
	
	float checkRange(float r){
		if(r<0)
			r = 0;
		else if(r > 1)
			r = 1;
		
		return r;
	}
	public Vector4f member;
	
	@Override
	public Vector4f get() {
		return member;
	}
	
	@Override
	public Vector3f get3f() {
		return new Vector3f(member.x,member.y,member.z);
	}
	
	@Override
	public void set(float r, float g, float b, float alpha) {
		setRed(r);
		setGreen(g);
		setBlue(b);
		setAlpha(alpha);
	}
	
	@Override
	public void set(float r, float g, float b) {
		setRed(r);
		setGreen(g);
		setBlue(b);
	}
	
	@Override
	public void set(Vector3f color) {
		setRed(color.x);
		setGreen(color.y);
		setBlue(color.z);
	}

	@Override
	public void set(Vector4f param) {
		if(null == param)
			return;
		
		setRed(param.x);
		setGreen(param.y);
		setBlue(param.z);
		setAlpha(param.w);
	}

	@Override
	public float getRed() {
		return member.x;
	}

	@Override
	public void setRed(float r) {
		member.x = checkRange(r);
	}

	@Override
	public float getGreen() {
		return member.y;
	}

	@Override
	public void setGreen(float g) {
		member.y = checkRange(g);
	}

	@Override
	public float getBlue() {
		return member.z;
	}

	@Override
	public void setBlue(float b) {
		member.z = checkRange(b);
	}

	@Override
	public float getAlpha() {
		return member.w;
	}

	@Override
	public void setAlpha(float alpha) {
		member.w = checkRange(alpha);
	}

}
