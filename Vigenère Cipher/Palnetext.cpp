#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std;
int main() {
	cout << "enter the number of keys: ";
	int keys_number;
	cin >> keys_number;
	cout << endl;

	cout << "enter the keys: ";
	int* keys = new int[keys_number];
	for (int i = 0; i < keys_number; i++) {
		int x;
		cin >> x;
		keys[i] = x;
	}
	cout << endl;

	cout << "enter the ciphertext: ";
	string ciphertext;
	cin >> ciphertext;
	cout << "\n";

	int key_index = 0;
	string planetext = "";
	for (int i = 0; i < ciphertext.length(); i++) {
		if (key_index == keys_number)
			key_index = 0;

		if(ciphertext[i] >= 'A' && ciphertext[i] <= 'Z'){
			int x = (int)ciphertext[i] - 65;
			x = ((x - keys[key_index]) + 26)%26;
			x += 65;
			planetext += char(x);
			key_index++;
		}
		else if(ciphertext[i] >= 'a' && ciphertext[i] <= 'z'){
			int x = (int)ciphertext[i] - 97;
			x = ((x - keys[key_index]) + 26) % 26;
			x += 97;
			planetext += char(x);
			key_index++;
		}
		else {
			planetext += ciphertext[i];
		}

	}
	cout << "planetext = " << planetext << endl;
}
