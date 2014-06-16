package a4;

public interface ICollider {
	public boolean collidesWith(ICollider go);
	public void handleCollision(ICollider go);
}
