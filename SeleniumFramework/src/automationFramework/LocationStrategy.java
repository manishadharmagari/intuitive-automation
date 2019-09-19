package automationFramework;

import org.openqa.selenium.By;

public enum LocationStrategy {

	ID("id") {
		@Override
		public By getBy(String identifier) {
			return By.id(identifier);
		}
	}, NAME("name") {
		@Override
		public By getBy(String identifier) {
			return By.name(identifier);
		}
	}, CLASSNAME("className") {
		@Override
		public By getBy(String identifier) {
			return By.className(identifier);
		}
	}, XPATH("xpath") {
		@Override
		public By getBy(String identifier) {
			return By.xpath(identifier);
		}
	}, LINKTEXT("linkText") {
		@Override
		public By getBy(String identifier) {
			return By.linkText(identifier);
		}
	};

	private String locationStartegy;

	LocationStrategy(String locationStartegy) {
		this.locationStartegy = locationStartegy;
	}

	/**
	 * returns LocationStrategy object by name
	 *
	 * @param name name of browser
	 * @return LocationStrategy object
	 */
	public static LocationStrategy getByName(String name) {
		for (LocationStrategy locationStrategy : values()) {
			if (locationStrategy.getLocationStrategyName().equalsIgnoreCase(name)) {
				return locationStrategy;
			}
		}
		return null;
	}

	public String getLocationStrategyName() {
		return locationStartegy;
	}

	public abstract By getBy(String identifier);

}
