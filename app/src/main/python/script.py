import smtplib

from email.message import EmailMessage

import ssl
import smtplib

def great(i):
    email_sender = 'xyz@gmail.com'
    email_password = ''
    email_receiver = i

    subject = 'Order Confirmed'

    body = '''Hi. 
    Your Order for products are confirmed.
    Thank you for ordering.
    Your products will be delivered by the next 2 days.
    Email cancel for cancelling the order.
    '''

    em = EmailMessage()
    em['From'] = email_sender
    em['To'] = email_receiver
    em['subject'] = subject
    em.set_content(body)

    context = ssl.create_default_context()

    with smtplib.SMTP_SSL('smtp.gmail.com', 465, context = context) as smtp:
        smtp.login(email_sender, email_password)
        smtp.sendmail(email_sender, email_receiver, em.as_string())