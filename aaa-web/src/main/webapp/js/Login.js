function postData(data) {
    fetch(config.loginUrl + "?json=" + JSON.stringify(data), {
        method: 'POST'
    }).then(function (response) {
        return response.text();
    }).then(function (text) {
        codeToName(text);
    }).catch(err => err);
}

function codeToName(code) {
    switch (code) {
        case "0":
            alert("SUCCESS");
            break;
        case "1":
            alert("HELP");
            break;
        case "2":
            alert("BAD LOGIN FORMAT");
            break;
        case "3":
            alert("BAD LOGIN");
            break;
        case "4":
            alert("BAD PASSWORD");
            break;
        case "5":
            alert("BAD ROLE");
            break;
        case "6":
            alert("NOT PERMISSION");
            break;
        case "7":
            alert("INVALID DATE");
            break;
        case "8":
            alert("INVALID VOLUME");
            break;

    }
}

class LoginMenu extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            login: '',
            pass: '',
            role: '',
            res: '',
            ds: '',
            de: '',
            vol: '',
        };

        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleInputChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;

        this.setState({
            [name]: value
        });
    }

    handleSubmit(event) {
        postData(this.state);
        event.preventDefault();
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>

                {/* Type 3*/}
                <p>Login: <input
                    name={"login"}
                    type={"text"}
                    value={this.state.login}
                    onChange={this.handleInputChange}/>
                </p>
                <p>Password: <input
                    name={"pass"}
                    type={"text"}
                    value={this.state.pass}
                    onChange={this.handleInputChange}/>
                </p>

                {/* Type 2*/}
                <p>Role: <input
                    name={"role"}
                    type={"text"}
                    value={this.state.role}
                    onChange={this.handleInputChange}/>
                </p>
                <p>Res: <input
                    name={"res"}
                    type={"text"}
                    value={this.state.res}
                    onChange={this.handleInputChange}/>
                </p>

                {/* Type 3 */}
                <p>DateStart: <input
                    name={"ds"}
                    type={"text"}
                    value={this.state.ds}
                    onChange={this.handleInputChange}/>
                </p>
                <p>DateEnd: <input
                    name={"de"}
                    type={"text"}
                    value={this.state.de}
                    onChange={this.handleInputChange}/>
                </p>
                <p>Volume: <input
                    name={"vol"}
                    type={"text"}
                    value={this.state.vol}
                    onChange={this.handleInputChange}/>
                </p>
                <input type={"submit"} value={"Send"}/>
            </form>
        );
    }
}