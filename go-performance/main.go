package main

import (
	"database/sql"
	"encoding/json"
	"fmt"
	"github.com/go-sql-driver/mysql"
	"github.com/gorilla/mux"
	"log"
	"net/http"
	//"encoding/json"
	//"github.com/gorilla/mux"
)

var db *sql.DB

func main() {

	cfg := mysql.Config{
		User:   "root",
		Passwd: "my-secret-pw",
		//User:                 os.Getenv("root"),
		//Passwd:               os.Getenv("test"),
		Net:                  "tcp",
		Addr:                 "127.0.0.1:3306",
		DBName:               "performance",
		AllowNativePasswords: true,
	}
	// Get a database handle.
	var err error
	db, err = sql.Open("mysql", cfg.FormatDSN())
	if err != nil {
		log.Fatal(err)
	}

	pingErr := db.Ping()
	if pingErr != nil {
		log.Fatal(pingErr)
	}
	fmt.Println("Connected!")

	router := mux.NewRouter()
	router.HandleFunc("/api/v1/account", PostAccount).Methods("POST")
	router.HandleFunc("/api/v1/transaction", PostTransaction).Methods("POST")

	log.Fatal(http.ListenAndServe(":8080", router))

}

func PostTransaction(w http.ResponseWriter, r *http.Request) {

	//time.Sleep(8 * time.Second)

	var transaction Transaction
	json.NewDecoder(r.Body).Decode(&transaction)
	db.Exec("INSERT INTO transactions(account, tvalue, type, created_at) VALUES(?, ?, ?, CURRENT_TIMESTAMP())", transaction.Account, transaction.Tvalue, transaction.Ttype)
	db.Exec("UPDATE accounts SET balance = balance + ? WHERE account = ?", transaction.Tvalue, transaction.Account)
}

func PostAccount(w http.ResponseWriter, r *http.Request) {
	var account Account
	json.NewDecoder(r.Body).Decode(&account)
	db.Exec("INSERT INTO accounts(account, balance) VALUES(?,?)", account.Account, account.Balance)
}

type Account struct {
	Id      int64   `json:"id"`
	Account int64   `json:"account"`
	Balance float32 `json:"balance"`
}

type Transaction struct {
	Id      int64
	Account int32
	Tvalue  float32
	Ttype   string
}
