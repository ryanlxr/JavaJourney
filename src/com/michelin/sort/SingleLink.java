package com.michelin.sort;

/**
 * 单链表的插入与逆置.
 * @author Lin.xr
 */
public class SingleLink {
	private int value;
	private SingleLink next;

	void display(SingleLink head) {
		SingleLink list = head;
		while (list != null) {
			System.out.print(list.value + ", ");
			list = list.next;
		}
	}
	
	void creatSingleLinkByHead(SingleLink head) {
		int n = 9;
		while(n > 0) {
			SingleLink node = new SingleLink();
			node.value = n;
			node.next = head.next;
			head.next = node;
			n--;
		}
	}
	
	void creatSingleLinkByTail(SingleLink head) {
		int n = 9;
		
		while(n > 0) {
			SingleLink node = new SingleLink();
			node.value = n;
			head.next = node;
			head = node;
			n--;
		}
	}
	
	SingleLink reverse(SingleLink head) {
		SingleLink current, p;
		if(head == null) {
			return null;
		}
		current = head.next;
		while(current.next != null) {
			p = current.next;
			current.next = p.next;
			p.next = head.next;
			head.next = p;
		}
		return head;
	}

	public static void main(String[] args) {
		SingleLink list = new SingleLink();
//		list.creatSingleLinkByHead(list);
//		list.display(list);
		list.creatSingleLinkByTail(list);
		list.display(list);
		list.display(list.reverse(list));
	}
}
