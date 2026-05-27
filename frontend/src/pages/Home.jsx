import { useState } from "react";

import API from "../services/api";

function Home() {

    const [result, setResult] = useState("");

    const add = async () => {

        try {

            const response = await API.post(

                "/api/v1/quantity/add",

                {
                    q1: {
                        value: 1,
                        unit: "FEET",
                        measurementType: "length"
                    },

                    q2: {
                        value: 12,
                        unit: "INCHES",
                        measurementType: "length"
                    }
                }
            );

            setResult(response.data);

        } catch (error) {

            console.log(error);
        }
    };

    return (

        <div>

            <h1>
                Quantity Measurement
            </h1>

            <button onClick={add}>
                Add
            </button>

            <h2>
                Result: {result}
            </h2>

        </div>
    );
}

export default Home;