import random

name = "Derrick"
#name = "" #to trigger alternative situation if user doesn't provide name
question = "Will I learn python in a week?"
#question = ""
answer = " "
random_number = random.randint(1, 12)
print("Number:", random_number)
if random_number == 1:
  answer = "Yes - definitely"
elif random_number == 2:
  answer = "It is decidedly so"
elif random_number == 3:
  answer = "Without a doubt"
elif random_number == 4:
  answer = "Reply hazy, try again"
elif random_number == 5:
  answer = "Ask again later"
elif random_number == 6:
  answer = "Better not tell you now"
elif random_number == 7:
  answer = "My sources say no"
elif random_number == 8:
  answer = "Outlook not so good"
elif random_number == 9:
  answer = "Very doubtful"
elif random_number == 10:
  answer = "Batteries need to be replaced"
elif random_number == 11:
  answer = "Glitch, please try again"
elif random_number == 12:
  answer = "No - definitely"
else:
  answer = "Error"

if name == "" and question != "":
  print("Question:", question)
elif name == "" and question == "":
  print("No question provided, cannot provide fortune")
elif name != "" and question == "":
  print("No question provided, cannot provide fortune")
else:
  print(name, "asks:", question)

if question != "":
  print("Magic 8-Ball's answer:", answer)