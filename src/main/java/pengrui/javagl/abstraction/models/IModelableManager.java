package pengrui.javagl.abstraction.models;

import pengrui.javagl.abstraction.util.LoaderUtil;
import pengrui.javagl.abstraction.util.ResourceUtil;

public interface IModelableManager {
	VaoPair vp =LoaderUtil.loadObjModel(ResourceUtil.getResource("loader/box.obj"));
}
