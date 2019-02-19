package pengrui.javagl.abstraction.shaders;

import pengrui.javagl.abstraction.util.ResourceUtil;
import pengrui.javagl.shader.ShaderImpl;

public interface IShaderableManager {
	Shaderable cache = new ShaderImpl(ResourceUtil.getResource("shader/lightvs"),ResourceUtil.getResource("shader/lightfs"));
	
}
