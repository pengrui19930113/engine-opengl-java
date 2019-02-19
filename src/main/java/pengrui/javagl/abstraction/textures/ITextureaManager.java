package pengrui.javagl.abstraction.textures;

import pengrui.javagl.abstraction.util.LoaderUtil;
import pengrui.javagl.abstraction.util.ResourceUtil;
import pengrui.javagl.texture.ImageType;

public interface ITextureaManager {

	int cache = LoaderUtil.loadTexture(ResourceUtil.getResource("texture/box.png"), ImageType.PNG);
}
