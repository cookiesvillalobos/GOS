package Server;

public class AVLTree {
	 
    public NodeAVL root;
 
    public static class NodeAVL {
        public Dragon data;
        private int balance;
        private int height;
        private NodeAVL left;
        private NodeAVL right;
        private NodeAVL parent;
 
        NodeAVL(Dragon key, NodeAVL parent) {
            this.data = key;
            this.parent = parent;
        }
    }
 
    public boolean insert(Dragon key) {
        if (root == null) {
            root = new NodeAVL(key, null);
            return true;
        }
 
        NodeAVL n = root;
        while (true) {
            if (n.data == key)
                return false;
 
            NodeAVL parent = n;
 
            boolean goLeft = n.data.edad > key.edad;
            n = goLeft ? n.left : n.right;
 
            if (n == null) {
                if (goLeft) {
                    parent.left = new NodeAVL(key, parent);
                } else {
                    parent.right = new NodeAVL(key, parent);
                }
                rebalance(parent);
                break;
            }
        }
        return true;
    }
 
    private void delete(NodeAVL nodeAVL) {
        if (nodeAVL.left == null && nodeAVL.right == null) {
            if (nodeAVL.parent == null) {
                root = null;
            } else {
                NodeAVL parent = nodeAVL.parent;
                if (parent.left == nodeAVL) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                rebalance(parent);
            }
            return;
        }
 
        if (nodeAVL.left != null) {
            NodeAVL child = nodeAVL.left;
            while (child.right != null) child = child.right;
            nodeAVL.data = child.data;
            delete(child);
        } else {
            NodeAVL child = nodeAVL.right;
            while (child.left != null) child = child.left;
            nodeAVL.data = child.data;
            delete(child);
        }
    }
 
    public void delete(Dragon delKey) {
        if (root == null)
            return;
 
        NodeAVL child = root;
        while (child != null) {
            NodeAVL nodeAVL = child;
            child = delKey.edad >= nodeAVL.data.edad ? nodeAVL.right : nodeAVL.left;
            if (delKey == nodeAVL.data) {
                delete(nodeAVL);
                return;
            }
        }
    }
 
    private void rebalance(NodeAVL n) {
        setBalance(n);
 
        if (n.balance == -2) {
            if (height(n.left.left) >= height(n.left.right))
                n = rotateRight(n);
            else
                n = rotateLeftThenRight(n);
 
        } else if (n.balance == 2) {
            if (height(n.right.right) >= height(n.right.left))
                n = rotateLeft(n);
            else
                n = rotateRightThenLeft(n);
        }
 
        if (n.parent != null) {
            rebalance(n.parent);
        } else {
            root = n;
        }
    }
 
    private NodeAVL rotateLeft(NodeAVL a) {
 
        NodeAVL b = a.right;
        b.parent = a.parent;
 
        a.right = b.left;
 
        if (a.right != null)
            a.right.parent = a;
 
        b.left = a;
        a.parent = b;
 
        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }
 
        setBalance(a, b);
 
        return b;
    }
 
    private NodeAVL rotateRight(NodeAVL a) {
 
        NodeAVL b = a.left;
        b.parent = a.parent;
 
        a.left = b.right;
 
        if (a.left != null)
            a.left.parent = a;
 
        b.right = a;
        a.parent = b;
 
        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }
 
        setBalance(a, b);
 
        return b;
    }
 
    private NodeAVL rotateLeftThenRight(NodeAVL n) {
        n.left = rotateLeft(n.left);
        return rotateRight(n);
    }
 
    private NodeAVL rotateRightThenLeft(NodeAVL n) {
        n.right = rotateRight(n.right);
        return rotateLeft(n);
    }
 
    private int height(NodeAVL n) {
        if (n == null)
            return -1;
        return n.height;
    }
 
    private void setBalance(NodeAVL... nodes) {
        for (NodeAVL n : nodes) {
            reheight(n);
            n.balance = height(n.right) - height(n.left);
        }
    }
 
    public void printBalance() {
        printBalance(root);
    }
 
    private void printBalance(NodeAVL n) {
        if (n != null) {
            printBalance(n.left);
            System.out.printf("%s ", n.balance);
            printBalance(n.right);
        }
    }
 
    private void reheight(NodeAVL nodeAVL) {
        if (nodeAVL != null) {
            nodeAVL.height = 1 + Math.max(height(nodeAVL.left), height(nodeAVL.right));
        }
    }
 
}