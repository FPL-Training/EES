package com.fastprac.sees.model.tool;

import com.fastprac.sees.model.Dimension;
import com.fastprac.sees.model.Location;

public class ToolPanel extends Panel {
	private Toolbar toolbar;

	public ToolPanel() {
		toolbar = null;
	}

	public ToolPanel(Location loc, Dimension dimension) {
		super(loc, dimension);
		addToolbar();
	}

	private void addToolbar() {
		int panelWidth = getDimension().getWidth();
		int panelHeight = getDimension().getHeight();
		int panelX = getLoc().getX();
		int panelY = getLoc().getY();

		Button startBtn = createStartButton(panelX, panelY, panelWidth, panelHeight);
		Button stopBtn = createStopButton(panelX, panelY, panelWidth, panelHeight);
		Button resetBtn = createResetButton(panelX, panelY, panelWidth, panelHeight);

		toolbar = new Toolbar(startBtn, stopBtn, resetBtn);
		toolbar.disable();
	}

	private Button createStartButton(int panelX, int panelY, int panelWidth, int panelHeight) {
		int btnW = panelWidth / 20;
		int btnH = panelHeight * 3 / 5;
		int btnX = panelX + panelWidth * 4 / 5;
		int btnY = panelY + panelHeight / 5;
		Button button = new Button(ButtonType.START, new Location(btnX, btnY), new Dimension(btnW, btnH));
		return button;
	}

	private Button createStopButton(int panelX, int panelY, int panelWidth, int panelHeight) {
		int btnW = panelWidth / 20;
		int btnH = panelHeight * 3 / 5;
		int btnX = panelX + panelWidth * 4 / 5 + (btnW + 2);
		int btnY = panelY + panelHeight / 5;
		Button button = new Button(ButtonType.STOP, new Location(btnX, btnY), new Dimension(btnW, btnH));
		return button;
	}

	private Button createResetButton(int panelX, int panelY, int panelWidth, int panelHeight) {
		int btnW = panelWidth / 20;
		int btnH = panelHeight * 3 / 5;
		int btnX = panelX + panelWidth * 4 / 5 + (btnW + 2) * 2;
		int btnY = panelY + panelHeight / 5;
		Button button = new Button(ButtonType.RESET, new Location(btnX, btnY), new Dimension(btnW, btnH));
		return button;
	}

	public Toolbar getToolbar() {
		return toolbar;
	}

	public void draw() {
		super.draw();
		toolbar.draw();
	}
}
