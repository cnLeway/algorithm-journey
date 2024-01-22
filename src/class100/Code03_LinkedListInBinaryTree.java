package class100;

// 测试链接 : https://leetcode.cn/problems/linked-list-in-binary-tree/
public class Code03_LinkedListInBinaryTree {

	// 不要提交这个类
	public class ListNode {
		int val;
		ListNode next;
	}

	// 不要提交这个类
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	// 提交以下正式方法
	public static boolean isSubPath(ListNode head, TreeNode root) {
		int m = 0;
		ListNode tmp = head;
		while (tmp != null) {
			m++;
			tmp = tmp.next;
		}
		int[] match = new int[m];
		m = 0;
		while (head != null) {
			match[m++] = head.val;
			head = head.next;
		}
		int[] next = nextArray(match, m);
		return find(root, 0, match, next);
	}

	public static boolean find(TreeNode cur, int mi, int[] match, int[] next) {
		if (mi == match.length) {
			return true;
		}
		if (cur == null) {
			return false;
		}
		while (mi >= 0 && cur.val != match[mi]) {
			mi = next[mi];
		}
		return find(cur.left, mi + 1, match, next) || find(cur.right, mi + 1, match, next);
	}

	public static int[] nextArray(int[] match, int m) {
		if (m == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[m];
		next[0] = -1;
		next[1] = 0;
		int i = 2, cn = 0;
		while (i < m) {
			if (match[i - 1] == match[cn]) {
				next[i++] = ++cn;
			} else if (cn > 0) {
				cn = next[cn];
			} else {
				next[i++] = 0;
			}
		}
		return next;
	}

}
