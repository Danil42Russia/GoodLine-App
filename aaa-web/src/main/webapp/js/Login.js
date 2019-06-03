function createBlogPost(data) {
    const formData = new FormData(data);

    fetch(config.loginUrl, {
        method: 'POST',
        body: formData,
    }).then(function (response) {
        console.log(response.text())
    }).catch(err => err);
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
        createBlogPost(this.form);
        event.preventDefault();
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit} ref={el => (this.form = el)}>

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