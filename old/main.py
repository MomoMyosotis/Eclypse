# first line

import tkinter as tk
from dispatcher import dispatch_command


if __name__ == "__main__":
    root = tk.Tk()
    app = AssistantUI(root)
    root.mainloop()
    quack = dispatch_command("quack")
    print(quack)



# last line