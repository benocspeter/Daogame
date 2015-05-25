package dao;

/**
 * The class for storing the coordinates which are used during steps.
 */
public class StepCoordinates {
	
	
	/**
	 * X coordinate of the disk to be moved.
	 */
	private int fromX;
	
	
	/**
	 * Y coordinate of the disk to be moved.
	 */
	private int fromY;
	
	
	/**
	 * X coordinate of the target field.
	 */
	private int toX;
	
	/**
	 * Y coordinate of the target field.
	 */
	private int toY;


	/**
	 * Constructor of this object.
	 * 
	 * @param fromX
	 *            X coordinate of the disk to be moved.
	 * @param fromY
	 *            Y coordinate of the disk to be moved.
	 * @param toX
	 *            X coordinate of the target field.
	 * @param toY
	 *            Y coordinate of the target field.
	 */
	
	public StepCoordinates(char fromX, char fromY, char toX,char toY) {
		super();
		this.fromX = fromX -'0';
		this.fromY = fromY -'0';
		this.toX = toX -'0';
		this.toY = toY -'0';
	}
	
	
	
	/**
	 * Returns the x coordinate of the disk to be moved.
	 * 
	 * @return The x coordinate of the disk to be moved.
	 */
	public int getFromX() {
		return fromX;
	}
	
	/**
	 * Sets the x coordinate of the disk to be moved.
	 * 
	 * @param fromX
	 *            The x coordinate of the disk to be moved.
	 */
	public void setFromX(int fromX) {
		this.fromX = fromX;
	}
	
	/**
	 * Returns the y coordinate of the disk to be moved.
	 * 
	 * @return The y coordinate of the disk to be moved.
	 */
	public int getFromY() {
		return fromY;
	}
	
	/**
	 * Sets the y coordinate of the disk to be moved.
	 * 
	 * @param fromY
	 *            The y coordinate of the disk to be moved.
	 */
	public void setFromY(int fromY) {
		this.fromY = fromY;
	}
	
	/**
	 * Returns the x coordinate of the target field.
	 * 
	 * @return The x coordinate of the target field.
	 */
	public int getToX() {
		return toX;
	}
	
	/**
	 * Sets the x coordinate of the target field.
	 * 
	 * @param toX
	 *            The x coordinate of the target field.
	 */
	public void setToX(int toX) {
		this.toX = toX;
	}
	
	
	/**
	 * Returns the y coordinate of the target field.
	 * 
	 * @return The y coordinate of the target field.
	 */
	public int getToY() {
		return toY;
	}
	
	
	/**
	 * Sets the y coordinate of the target field.
	 * 
	 * @param toY
	 *            The y coordinate of the target field.
	 */
	public void setToY(int toY) {
		this.toY = toY;
	}
	
	
	 
	
	

}
