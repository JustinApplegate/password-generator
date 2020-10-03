all:
	g++ -o PasswordGenerator PasswordGenerator.cpp
	./PasswordGenerator $(pl)
clean:
	rm PasswordGenerator