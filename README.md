# ac2

#Nome: Guilherme Pereira da Silva
#RA: 190570

#Erro 1 - erro de inserção SQL - TABLE_EVENTS
#Erro 2 - Admin entity = adminRepository.getOne(id) - DEPRECATED
#Erro 3 - Application error (HEROKU)


#JSON:
admin:
{
    "id": 1,
    "name": "Fiódor",
    "email": "Fiódor@gmail.com",
    "phoneNumber": "(033) 1515-1515"
}

attend:
{
    "id": 1,
    "name": Thomas
    "email": "Thomas@email.com",
    "balance": "1"
}

event:
{
    "id": 1,
    "name": "Movie theater",
    "description": "Movie theater NY",
    "startDate": "2000-05-05",
    "endDate": "2000-05-05",
    "startTime": "03:03:03",
    "endTime": "06:33:33",
    "emailContact": "Moviethater@email.com",
    "amountFreeTickets": 100,
    "amountPayedTickets": 100,
    "priceTicket": 1,
    "admin": 1,
}

place:
{
    "id": 1,
    "name": "Lugar1",
    "address": "Rua1"
}
