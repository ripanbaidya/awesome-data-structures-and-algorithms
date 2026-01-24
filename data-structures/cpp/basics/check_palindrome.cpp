#include <algorithm>
#include <iostream>
#include <string>

using namespace std;

// Function to check if a string is a palindrome
bool isPalindrome(const string &text) {
  string reversed = text;
  reverse(reversed.begin(), reversed.end());
  return text == reversed;
}

int main() {
  string input;
  cout << "Enter a string to check if it's a palindrome: ";

  // Using getline to handle phrases with spaces
  if (getline(cin, input)) {
    if (isPalindrome(input)) {
      cout << "\"" << input << "\" is a palindrome!" << endl;
    } else {
      cout << "\"" << input << "\" is not a palindrome." << endl;
    }
  }

  return 0;
}
