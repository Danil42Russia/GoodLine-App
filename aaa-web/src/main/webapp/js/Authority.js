class Authority extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            data: []
        }
    }

    async componentWillMount() {
        const res = await fetch('/ajax/authority');
        const data = await res.json();
        this.setState({data: data});
    }

    render() {
        return (
            <div>
                <table>
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>id_user</th>
                        <th>id_role</th>
                        <th>res</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.data.map(item => (
                            <tr key={item.id}>
                                <td>{item.id}</td>
                                <td>{item.id_user}</td>
                                <td>{item.id_role}</td>
                                <td>{item.res}</td>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
            </div>
        )
    }
}