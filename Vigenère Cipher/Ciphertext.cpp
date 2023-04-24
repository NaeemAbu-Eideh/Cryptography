#include<iostream>
#include<cstdlib>
#include<random>
using namespace std;
void main() {

	srand(time(nullptr));

	int key_numbers;
	cout << "enter the number of keys you need to encryption the text: ";
	cin >> key_numbers;
	cout << endl;

	int * key = new int[key_numbers];

	cout << "the keys is: ";
    for(int i=0;i<key_numbers;i++){
		key[i] = rand() % 26;
		cout << key[i] << " ";
    }
	cout << "\n";


	string planetext;
	cout << "enter the planetext: ";
	cin >> planetext;
	cout << endl;

	int key_index=0;
	string ciphertext = "";
	for (int i = 0; i < planetext.length(); i++) {
		if (key_index == key_numbers)
			key_index = 0;
		if (planetext[i] >= 'a' && planetext[i] <= 'z') {
			int x = ((int)planetext[i]) - 97;
			x = x + key[key_index];
			x = (x % 26) + 97;
			ciphertext += (char)x;
			key_index++;
		}
		else if (planetext[i] >= 'A' && planetext[i] <= 'Z') {
			int x = ((int)planetext[i]) - 65;
			x = x + key[key_index];
			x = (x % 26) + 65;
			ciphertext += (char)x;
			key_index++;
		}
		else{
			ciphertext += planetext[i];
		}
	}

	cout << "ciphertext: " << ciphertext << endl;



}