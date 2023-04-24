#include<iostream>
using namespace std;

void dycreotion(string text) {
	string word = "";

	for (int i = 0; i < 25; i++) {
		for (int j = 0; j < text.length(); j++) {
			if (text[j] >= 'A' && text[j] <= 'Z') {
				int x = (int)text[j] - 65;
				x = (((x - i) + (25)) % 26) + 65;
				word += (char)x;

			}
			else if (text[j] >= 'a' && text[j] <= 'z') {
				int x = (int)text[j] - 97;
				x = (((x - i) + (25)) % 26) + 97;
				word += (char)x;
			}
			else {
				word += text[j];
			}
		}
		cout <<"Key "<<i+1<<" : "<< word << endl;
		word = "";
	}


}

void main() {
	string word;
	cout << "enter the ciphertext:";
	cin >> word;
	cout << "\n============\n";
	dycreotion(word);
}