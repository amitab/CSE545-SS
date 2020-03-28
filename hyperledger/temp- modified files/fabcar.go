/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/*
 * The sample smart contract for documentation topic:
 * Writing Your First Blockchain Application
 */

package main

/* Imports
 * 4 utility libraries for formatting, handling bytes, reading and writing JSON, and string manipulation
 * 2 specific Hyperledger Fabric specific libraries for Smart Contracts
 */
import (
	"bytes"
	"encoding/json"
	"fmt"
	"strconv"

	"github.com/hyperledger/fabric/core/chaincode/shim"
	sc "github.com/hyperledger/fabric/protos/peer"
)

// Define the Smart Contract structure
type SmartContract struct {
}

// Define the Transaction structure, with 4 properties.  Structure tags are used by encoding/json library
type Transaction struct {
	TxnID   string `json:"txnid"`
	Date  string `json:"date"`
	Time string `json:"time"`
	Amount  string `json:"amount"`
}

/*
 * The Init method is called when the Smart Contract "bankApp" is instantiated by the blockchain network
 * Best practice is to have any Ledger initialization in separate function -- see initLedger()
 */
func (s *SmartContract) Init(APIstub shim.ChaincodeStubInterface) sc.Response {
	return shim.Success(nil)
}

/*
 * The Invoke method is called as a result of an application request to run the Smart Contract "bankApp"
 * The calling application program has also specified the particular smart contract function to be called, with arguments
 */
func (s *SmartContract) Invoke(APIstub shim.ChaincodeStubInterface) sc.Response {

	// Retrieve the requested Smart Contract function and arguments
	function, args := APIstub.GetFunctionAndParameters()
	// Route to the appropriate handler function to interact with the ledger appropriately
	if function == "queryTransaction" {
		return s.queryTransaction(APIstub, args)
	} else if function == "initLedger" {
		return s.initLedger(APIstub)
	} else if function == "createTransaction" {
		return s.createTransaction(APIstub, args)
	} else if function == "queryAllTransactions" {
		return s.queryAllTransactions(APIstub)
	} 
	// else if function == "changeTransactionStatus" {
	// 	return s.changeTransactionStatus(APIstub, args)
	// }

	return shim.Error("Invalid Smart Contract function name.")
}

func (s *SmartContract) queryTransaction(APIstub shim.ChaincodeStubInterface, args []string) sc.Response {

	if len(args) != 1 {
		return shim.Error("Incorrect number of arguments. Expecting 1")
	}

	transactionAsBytes, _ := APIstub.GetState(args[0])
	return shim.Success(transactionAsBytes)
}

func (s *SmartContract) initLedger(APIstub shim.ChaincodeStubInterface) sc.Response {
	transactions := []Transaction{
		Transaction{TxnID: "1", Date: "03/24/2020", Time: "10:10", Amount: "100"},
		Transaction{TxnID: "2", Date: "03/24/2020", Time: "10:20", Amount: "10"},
		Transaction{TxnID: "3", Date: "03/24/2020", Time: "12:20", Amount: "20"},
		Transaction{TxnID: "4", Date: "03/24/2020", Time: "13:10", Amount: "200"},
		Transaction{TxnID: "5", Date: "03/24/2020", Time: "15:10", Amount: "1200"},
		Transaction{TxnID: "6", Date: "03/24/2020", Time: "18:10", Amount: "1000"},
		Transaction{TxnID: "7", Date: "03/24/2020", Time: "19:00", Amount: "100000"},
		Transaction{TxnID: "8", Date: "03/24/2020", Time: "19:50", Amount: "100"},
		Transaction{TxnID: "9", Date: "03/24/2020", Time: "21:00", Amount: "999"},
		Transaction{TxnID: "10", Date: "03/24/2020", Time: "23:00", Amount: "200"},
	}

	i := 0
	for i < len(transactions) {
		fmt.Println("i is ", i)
		transactionAsBytes, _ := json.Marshal(transactions[i])
		APIstub.PutState("TXN"+strconv.Itoa(i), transactionAsBytes)
		fmt.Println("Added", transactions[i])
		i = i + 1
	}

	return shim.Success(nil)
}

func (s *SmartContract) createTransaction(APIstub shim.ChaincodeStubInterface, args []string) sc.Response {

	if len(args) != 5 {
		return shim.Error("Incorrect number of arguments. Expecting 5")
	}

	var transaction = Transaction{TxnID: args[1], Date: args[2], Time: args[3], Amount: args[4]}

	transactionAsBytes, _ := json.Marshal(transaction)
	APIstub.PutState(args[0], transactionAsBytes)

	return shim.Success(nil)
}

func (s *SmartContract) queryAllTransactions(APIstub shim.ChaincodeStubInterface) sc.Response {

	startKey := "TXN0"
	endKey := "TXN999999"

	resultsIterator, err := APIstub.GetStateByRange(startKey, endKey)
	if err != nil {
		return shim.Error(err.Error())
	}
	defer resultsIterator.Close()

	// buffer is a JSON array containing QueryResults
	var buffer bytes.Buffer
	buffer.WriteString("[")

	bArrayMemberAlreadyWritten := false
	for resultsIterator.HasNext() {
		queryResponse, err := resultsIterator.Next()
		if err != nil {
			return shim.Error(err.Error())
		}
		// Add a comma before array members, suppress it for the first array member
		if bArrayMemberAlreadyWritten == true {
			buffer.WriteString(",")
		}
		buffer.WriteString("{\"Key\":")
		buffer.WriteString("\"")
		buffer.WriteString(queryResponse.Key)
		buffer.WriteString("\"")

		buffer.WriteString(", \"Record\":")
		// Record is a JSON object, so we write as-is
		buffer.WriteString(string(queryResponse.Value))
		buffer.WriteString("}")
		bArrayMemberAlreadyWritten = true
	}
	buffer.WriteString("]")

	fmt.Printf("- queryAllTransactions:\n%s\n", buffer.String())

	return shim.Success(buffer.Bytes())
}

func (s *SmartContract) changeTransactionStatus(APIstub shim.ChaincodeStubInterface, args []string) sc.Response {

	if len(args) != 2 {
		return shim.Error("Incorrect number of arguments. Expecting 2")
	}

	transactionAsBytes, _ := APIstub.GetState(args[0])
	transaction := Transaction{}

	json.Unmarshal(transactionAsBytes, &transaction)
	transaction.Status = args[1]

	transactionAsBytes, _ = json.Marshal(transaction)
	APIstub.PutState(args[0], transactionAsBytes)

	return shim.Success(nil)
}

// The main function is only relevant in unit test mode. Only included here for completeness.
func main() {

	// Create a new Smart Contract
	err := shim.Start(new(SmartContract))
	if err != nil {
		fmt.Printf("Error creating new Smart Contract: %s", err)
	}
}
