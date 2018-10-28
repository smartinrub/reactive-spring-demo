# reactive-spring-demo
## Set up
- [Install MongoDB on Ubuntu](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-ubuntu/)
- Start MongoDB
```sh
sudo service mongod start
```
- Insert a Member:
```sh
db.member.insertOne({_id:"1", name: "Sergio"})
```
- Find member inserted:
```sh
db.member.find( {id: "1"} )
```

