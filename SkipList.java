/*
Author: KayyDVC
Email: kayydv1016@gmail.com
Description: This is the meat of HW5. It implements a skip list using a
top-down approach. It contains a searchNode, addNode, removeNode, printList,
and subMap functionality using the timeDate object as the common "key" and
the activity being the "value,"
*/

import java.util.Objects;

public class SkipList {

	SkipNode head;
	SkipNode tail;
	Integer NEG_INFINITY = Integer.MIN_VALUE;
	Integer POS_INFINITY = Integer.MAX_VALUE;

	//adds the "random" probability function to the SkipList
	FakeRandHeight rand = new FakeRandHeight();

	//keeps track of List height. Should always be 1 less than actual list
	// height to ensure constant head/tail node values
	Integer listHeight = 0;

	public SkipList(){
		//set head and tail of skip list to positive and negative infinity to
		// ensure head/tail pointers are constant for comparisons in search
		// functionality

		head = new SkipNode(null, NEG_INFINITY);
		tail = new SkipNode(null, POS_INFINITY);

		//connects head and tail to each other
		head.setNext(tail);
		tail.setPrev(head);
	}

	//implements search function for list. Will always return a node
	// reference that contains a dateTime value that is less or equal to than
	// the dateTime value passed in
	public SkipNode searchList(Integer dateTime){

		SkipNode node = head;

		//ensures node always has a lower pointer that isn't empty.

		while(node.getLower()!= null){

			//Start each iteration by dropping a level. This ensures one
			// level will always contain only the POS Infinity "node" as head
			// and NEG Infinity "node" as tail of entire list
			node = node.lower;

			//continuously loops through all nodes on the same level until
			// there is a node with a dateTime value greater than the passed
			// val.
			while(node.getNext().getDateTime() <= dateTime){

				node = node.getNext();
			}
		}

		return node;
	}


	//inserts node into correct position in skip list
	//Starts from top most layer and adds a new layer recursively using
	// the fake rand generator.
	public void insertNode(SkipNode node){
		//hold reference to last node at the bottom layer of the search list
		// that has a dateTime value that is less than the new node's
		// dateTime value
		SkipNode positionToInsert = searchList(node.getDateTime());

		//will hold the unaltered reference to the node that we want to insert
		// the new node after
		SkipNode positionHolder;

		//will be a value ranging from 0-4; Skip list will never have a
		// maximum height of more than 6 levels in this application
		int levelToInsert = rand.get();

		for (int temp = 0; temp <=levelToInsert; temp++){

			//check if there are sufficient levels for insertion. There
			// should always be +1 actual levels than listHeight val
			if (listHeight<=temp){
				addLevel();
				listHeight++;
			}

			//if only inserting at bottom level, just set prev and next node
			// references
			if (temp == 0){
				node.setPrev(positionToInsert);
				node.setNext(positionToInsert.getNext());

				positionToInsert.getNext().setPrev(node);
				positionToInsert.setNext(node);
			}
			else {
				//ensures that on every iteration, the base node
				// (position-holder) is updated to reflect the correct level
				positionHolder = positionToInsert.getNext();

				//finds the node that has the last known upper ref so that if the
				// upper level get connected properly in the event that
				// the new node gets inserted into multiple levels
				while (positionToInsert.getUpper() == null) {
					positionToInsert = positionToInsert.getPrev();
				}

				//contains a ref the upper level's last node that has a
				// timeDate val less than the current node
				positionToInsert = positionToInsert.getUpper();

				//create new node containing matching data as the node
				// passed, and connect it to the correct next, prev, and
				// lower nodes
				SkipNode newNode = new SkipNode(node.getActivity(),
						node.getDateTime());

				newNode.setPrev(positionToInsert);
				newNode.setNext(positionToInsert.getNext());
				newNode.setLower(positionHolder);

				positionHolder.setUpper(newNode);
				positionToInsert.getNext().setPrev(newNode);
				positionToInsert.setNext(newNode);

			}
		}




	}

	public void addLevel(){
		//create two a new empty head/tail pointer and set connect them to
		// previous head and tail. Ensures there is always an empty level for
		// constant reference point
		SkipNode tempHead = new SkipNode(null, NEG_INFINITY);
		SkipNode tempTail = new SkipNode(null,POS_INFINITY);

		//connect temp head/tail to each other correctly
		tempHead.setNext(tempTail);
		tempTail.setPrev(tempHead);

		//connect temp head/tail to current head/tail nodes
		tempHead.setLower(head);
		tempTail.setLower(tail);

		//set current head/tail upper pointer to new head/tail nodes
		head.setUpper(tempHead);
		tail.setUpper(tempTail);

		//set head and tail to tempHead and tempTail
		head = tempHead;
		tail = tempTail;
	}

	public void removeNode (Integer dateTime){
		SkipNode toRemove = searchList(dateTime);

		//a node will always be removed once. However, once a node is
		// removed check the validity of the "upper" reference and if invalid
		// or null, stops the loop; Otherwise, continues the loop.
		do {
			SkipNode temp = toRemove.getUpper();

			if (toRemove.getLower()!=null){
				toRemove.setLower(null);
			}

			toRemove.getPrev().setNext(toRemove.getNext());
			toRemove.getNext().setPrev(toRemove.getPrev());
			toRemove.setUpper(null);

			toRemove = temp;

		}
		while (toRemove!=null);

		//remove level if there are more than 1 empty levels after calling
		// removeNode
		if (Objects.equals(head.getLower().getNext().getDateTime(), POS_INFINITY)){
			 removeLayer();
		}
	}

	//removes layer of SkipList from the top. Only to be used in
	// conjunction with removeNode();
	public void removeLayer(){
		head = head.getLower();
		tail = tail.getLower();
	}

	public void printList(){

		SkipNode currNode = head.getLower();

		//should always point to the first node (NEG_INFINITY) of each level
		// or null
		SkipNode nextLevel = currNode.getLower();

		//skip list always contains one more level than needed. Remove empty
		// level when printing list
		Integer printHeight = listHeight-1;

		while (currNode!=null){
			//excluded placeholder head/tail nodes
			currNode = currNode.getNext();
			//print list height at start of line
			System.out.print("(S"+ printHeight + ")  ");

			//executes as long as the end of the level hasn't been reached;
			// Prints time and date + activity in required format
			while (!Objects.equals(currNode.getDateTime(), POS_INFINITY)){
				System.out.print(currNode.getDateTime() + ":" + currNode.getActivity()+ "  ");
				currNode = currNode.getNext();
			}

			//sets the node back at the placeholder "head" node of the next
			// level
			currNode = nextLevel;

			printHeight--;
			System.out.println("");

			if (currNode != null){
				nextLevel = currNode.getLower();
			}



		}


	}

	public void subMap(Integer timeDate1, Integer timeDate2){
		SkipNode beginning =searchList(timeDate1);
		SkipNode end = searchList(timeDate2);

		//since searchList() returns a node that is less than or equal to the
		// timeDate passed, check the beginning and end values to make sure
		// they are within the timeframe specified.
		if(beginning.getDateTime()< timeDate1){
			beginning = beginning.getNext();
		}

		//move from beginning to end after getting node locations.
		while (beginning!=end){
			System.out.print(beginning.getDateTime() + ":" + beginning.getActivity()+ "  ");
			beginning = beginning.getNext();
		}
		//since the end wasn't printed, print the final dataTime/Activity node
		System.out.println(beginning.getDateTime() + ":" + beginning.getActivity()+ "  ");
	}
}
