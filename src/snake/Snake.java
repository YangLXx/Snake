package snake;

import java.util.LinkedList;

public class Snake {
    private LinkedList<Node> body = new LinkedList<>();

    public Snake(LinkedList<Node> body) {
        this.body = body;
    }

    public Node getHead() {
        return body.getFirst();
    }

    public Node getTail() {
        return body.getLast();
    }

    public LinkedList<Node> getBody() {
        return body;
    }

    private Node facingNode(Direction direction) {
        int newHeadX = getHead().X();
        int newHeadY = getHead().Y();

        switch (direction) {
            case UP: newHeadY = getHead().Y() - 1; break;
            case RIGHT: newHeadX = getHead().X() + 1; break;
            case DOWN: newHeadY = getHead().Y() + 1; break;
            case LEFT: newHeadX = getHead().X() - 1; break;
        }
        Node facingNode = new Node(newHeadX, newHeadY);

        return facingNode;
    }

    public Node move(Direction direction) {
        Node newHead = facingNode(direction);
        body.addFirst(newHead);

        Node tail= getTail();
        body.removeLast();

        return tail;
    }

    public void eat(Node removedTail) {
        body.addLast(removedTail);
    }
}
