#include<iostream>
using namespace std;
string encryption(string text, int key) {
	string word = "";

	for (int i = 0; i < text.length(); i++) {
		if (text[i] >= 'A' && text[i] <= 'Z') {
			int x = ((int)text[i]) - 65;
			x = (x + key) % 26;
			x += 65;
			word += (char)x;
		}
		else if (text[i] >= 'a' && text[i] <= 'z') {
			int x = ((int)text[i]) - 97;
			x = (x + key) % 26;
			x += 97;
			word += (char)x;
		}
		else {
			word += text[i];
		}
	}
	return word;

}

void main() {
	cout << "enter the key(1-25): ";
	int key;
	cin >> key;
	cout << endl;
	cout << "==============\n";
	cout << "enter a word:";
	string word;
	cin >> word;
	cout << "\n================\n";
	string word2="";
	word2 = encryption(word, key);
	cout << word2 << endl;
	cout << "======================\n";
	

}