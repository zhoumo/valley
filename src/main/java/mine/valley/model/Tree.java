package mine.valley.model;

import java.util.List;

import mine.valley.base.BaseModel;

@SuppressWarnings("serial")
public class Tree extends BaseModel {

	private String text;

	private Long id;

	private Long parent;

	private List<Tree> nodes;

	public Tree(String text, Long id) {
		this.text = text;
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParent() {
		return parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}

	public List<Tree> getNodes() {
		return nodes;
	}

	public void setNodes(List<Tree> nodes) {
		this.nodes = nodes;
	}
}
