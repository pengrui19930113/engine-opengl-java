package pengrui.javagl.abstraction.lighting;

import pengrui.javagl.abstraction.datas.Varaible3f;
import pengrui.javagl.abstraction.datas.impl.Color;
import pengrui.javagl.abstraction.datas.impl.Position3D;

public interface Lightable {
	Color getLightColor();
	void setLightColor(Color c);
	Position3D getLightPosition();
	void setLightPosition(Position3D pos);
	Varaible3f getAttenuation();
	void setAttenuation(Varaible3f v);
}
