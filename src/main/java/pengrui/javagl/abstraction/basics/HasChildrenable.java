package pengrui.javagl.abstraction.basics;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import pengrui.javagl.abstraction.util.CheckUtil;
import pengrui.javagl.abstraction.util.DebugUtil;
import pengrui.javagl.abstraction.util.ExceptionUtil;
import pengrui.javagl.abstraction.util.LogUtil;

@SuppressWarnings("all")
public interface HasChildrenable<T extends HasChildrenable<T>> {

	Collection<T> getChildren();
	/**
	 * 在当前子元素的容器中添加 child
	 * @param child
	 */
	void addChild(T child);
	/**
	 * 如果当前元素的子孙备中包含了child 则移除
	 * @param child
	 */
	void removeChild(T child);
	/**
	 * 当前元素的子元素是否包含 child(不会查找孙辈元素)
	 * @param child
	 * @return
	 */
	boolean hasTheChild(T child);
	T getParent();
	void setParent(T parent);
	void setDepth(int d);
	int getDepth();// 通常 深度 >= 1
	void printInfo(); // 为了调试方便

	static void printParamIsNullMessage() {
		LogUtil.debug("class is null , ignore");
	}

	static String getClassInfo(Class<?> clazz) {
		if (CheckUtil.paramNotNull(clazz))
			return String.format("classInfo:%s ", clazz);
		else {
			printParamIsNullMessage();
			return "";
		}
	}

	static String getClassInfo(Class<?> parentClass, Class<?> childClass) {
		if (CheckUtil.paramsAllNotNull(parentClass, childClass))
			return String.format("classInfo: parent:%s,child%s ", parentClass, childClass);
		else {
			printParamIsNullMessage();
			return "";
		}
	}

	public static <G extends HasChildrenable<G>> void removeChild(G parent, G child) {
		if(CheckUtil.paramsAllNotNull(parent,child)){
			
			List<G> childs = new LinkedList<G>();
			List<G> tmps = new LinkedList<G>();
			childs.add(parent);
			
			for(;;){
				if(childs.isEmpty())
					break;
				
				tmps.clear();
				for(G c:childs){
					if(c.getChildren().contains(child)){
						c.getChildren().remove(child);
						continue;
					}
					tmps.addAll(c.getChildren());
				}
				childs.clear();
			}
		}
	}
	public static <G extends HasChildrenable<G>> void setParent(G child, G parent) {

		if (null == child || null == parent) {
			LogUtil.debug(
					String.format("set parent param invalid,%s", getClassInfo(parent.getClass(), child.getClass())));
			return;
		}

		G pparent;
		while (null != (pparent = parent.getParent())) {
			if (child == pparent) {// 避免成环
				LogUtil.info(
						String.format("warning! parent's parents has child , that is loop , invalid set parent , %s",
								getClassInfo(parent.getClass(), child.getClass())));
				return;
			}
		}
		child.setParent(parent);
		if (parent.getDepth() < 1)
			LogUtil.info("warning! parent's depth is less than 1 , there is error maybe!");

		child.setDepth(parent.getDepth() + 1);
		DebugUtil.depthMessage("setDrawableParent", child.getDepth(),child.getClass());
	}

	/**
	 * 假如 child 是parent 的子孙元素 则不会添加
	 * @param parent
	 * @param child
	 */
	public static <G extends HasChildrenable<G>> void addChild(G parent, G child) {
		if (null == child || null == parent) {
			LogUtil.debug("add child param invalid");
			return;
		}

		if (parent.hasTheChild(child)) {
			DebugUtil.depthMessage("child's depth parent's class", child.getDepth(), parent.getClass());// TODO
		} else {
			parent.addChild(child);
		}
	}

	/**
	 * 如果 child 是parent 的子孙元素 则返回 true
	 * @param parent
	 * @param child
	 * @return
	 */
	public static <G extends HasChildrenable<G>> boolean hasTheChild(G parent, G child) {
		if (null == child || null == parent) {
			LogUtil.debug("test has the child param invalid");
			ExceptionUtil.throwRuntimeException("test has the child param invalid");
		}
		if (parent.getChildren().contains(child)) {
			return true;
		} else {
			for (G c : parent.getChildren())
				if (c.hasTheChild(child))
					return true;
		}

		return false;
	}

	public static <G extends HasChildrenable> void printTree(G hasChildrenable) {
		if (!CheckUtil.paramNotNull(hasChildrenable))
			return;

		List<G> ls = new LinkedList<G>(); // 当前层级指针
		List<G> tmp = new LinkedList<G>();
		ls.add(hasChildrenable);
		for (;;) {// 树的层级遍历
			if (ls.isEmpty())
				break;

			tmp.clear();
			for (G d : ls) {
				tmp.add(d);// 保留当前层的所有元素
				d.printInfo();
			}
			ls.clear();// 清除零级根元素
			for (G ds : tmp) {
				ls.addAll(ds.getChildren());// 用下面的循环效果一样
				// for (G dds : ds.getChildren())
				// ls.add(dds);// 将当前层指针指向 下一层 的所有元素
			}
		}
	}

	public static <G extends HasChildrenable<G>> void checkAndPrintTree(G hasChildrenable) {
		if (!CheckUtil.paramNotNull(hasChildrenable))
			return;

		G lastLater = hasChildrenable;
		G last = null;

		do {
			last = lastLater;
			if (lastLater.getDepth() > 1)
				continue;
		} while (null != (lastLater = hasChildrenable.getParent()));

		if (last.getDepth() > 1) {
			printTree(last);
			ExceptionUtil.throwRuntimeException("error raletive");
		}
		printTree(last);
	}

	public static <G extends HasChildrenable> void printInfo(G g) {
		if (CheckUtil.paramNotNull(g))
			LogUtil.debug(String.format("depth:%d,%s", g.getDepth(), g.toString()));
	}
}
