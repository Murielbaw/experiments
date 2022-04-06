import sys
import tkinter as tk

def compound_interest():
    """calculate principle+interest.
    """
    principle = ent_principle.get()
    final_result = float(principle) * (pow((1 + 20 / 100), -5))
    lbl_result["text"] = f"{round(final_result, 2)} lei"

window = tk.Tk()
window.title("How much you owe me")
window.resizable(width=False, height=False)
greetings = tk.Label(text="Enter the price of your stupid shoes+ the stupid wooden stuff which is 5500, \nclick on the arrow button, \nyou will get the final amount, \nyou don't need to know how the amount is calculated :) ",background="HotPink")


frm_entry = tk.Frame(master=window)
lbl_principle=tk.Label(master=frm_entry, text="Principle",background="HotPink")
ent_principle = tk.Entry(master=frm_entry, width=10)
lbl_currency = tk.Label(master=frm_entry, text="£",background="HotPink")


greetings.grid(row=0, column=0, sticky="w")
lbl_principle.grid(row=1, column=0, sticky="w")
ent_principle.grid(row=1, column=1)
lbl_currency.grid(row=1, column=2)

btn_convert = tk.Button(
    master=window,
    text="\N{RIGHTWARDS BLACK ARROW}",
    background="HotPink",
    command=compound_interest
)
lbl_result = tk.Label(master=window,background="HotPink")


ending=tk.Label(master=window, background="HotPink",text="Transfer the result amount to the following account by the end of today, otherwise you are dead, biu :)")
account_name=tk.Label(master=window, text="Name:Aiwei Bian",background="HotPink")
account_Sc=tk.Label(master=window, text="Sort Code: 30-90-89",background="HotPink")
account_No=tk.Label(master=window, text="Account: 76971860",background="HotPink")

frm_entry.grid(row=1, column=0, padx=10)
btn_convert.grid(row=1, column=1, pady=10)
lbl_result.grid(row=1, column=2, padx=10)


ending.grid(row=2, column=0, sticky="w")
account_name.grid(row=3, column=0, sticky="w")
account_Sc.grid(row=4, column=0, sticky="w")
account_No.grid(row=5, column=0, sticky="w")


window.mainloop()