def woof():
	return "woof"
	
def meow():
	return "meow"
	
def chirp():
	return "chirp"
	
switcher = {
	"dog": woof(),
	"cat": meow(),
	"bird": chirp()
	}
	
message = switcher.get("dog", "invalid")

print(message)